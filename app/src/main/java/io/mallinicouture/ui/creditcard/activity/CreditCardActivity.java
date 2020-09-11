package io.mallinicouture.ui.creditcard.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import javax.inject.Inject;

import io.mallinicouture.R;
import io.mallinicouture.base.BaseActivity;
import io.mallinicouture.databinding.ActivityCreditCardBinding;
import io.mallinicouture.ui.creditcard.adapter.CCViewPagerAdapter;
import io.mallinicouture.ui.creditcard.fragment.CCNameFragment;
import io.mallinicouture.ui.creditcard.fragment.CCNumberFragment;
import io.mallinicouture.ui.creditcard.fragment.CCSecureCodeFragment;
import io.mallinicouture.ui.creditcard.fragment.CCValidityFragment;
import io.mallinicouture.ui.creditcard.fragment.CardBackFragment;
import io.mallinicouture.ui.creditcard.fragment.CardFrontFragment;
import io.mallinicouture.ui.creditcard.utils.CreditCardUtils;
import lombok.Getter;

public class CreditCardActivity extends BaseActivity implements FragmentManager.OnBackStackChangedListener {
    private static final String TAG = "CreditCardActivity";

    private ActivityCreditCardBinding binding;

    @Getter
    @Inject CardFrontFragment cardFrontFragment;
    @Getter
    @Inject CardBackFragment cardBackFragment;

    @Getter
    @Inject CCNumberFragment numberFragment;
    @Getter
    @Inject CCNameFragment nameFragment;
    @Getter
    @Inject CCValidityFragment validityFragment;
    @Getter
    @Inject CCSecureCodeFragment secureCodeFragment;

    @Inject CCViewPagerAdapter adapter;

    private int itemN;
    private boolean backTrack = false;
    private boolean mShowingBack = false;

    private String cardNumber, cardCVV, cardValidity, cardName;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        // binding should be instantiated before dagger injection
        binding = ActivityCreditCardBinding.inflate(LayoutInflater.from(this));
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        if (savedInstanceState == null) {
            // Add fragment to 'frame_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, cardFrontFragment).commit();
        } else {
            mShowingBack = (getSupportFragmentManager().getBackStackEntryCount() > 0);
        }

        getSupportFragmentManager().addOnBackStackChangedListener(this);

        // Initializing viewPager
        setupViewPager();

        binding.btnNext.setOnClickListener(view -> {
            int pos = binding.viewpager.getCurrentItem();

            if (pos < itemN) {
                binding.viewpager.setCurrentItem(pos + 1);
            } else {
                checkEntries();
            }
        });
    }

    private void checkEntries() {

        cardName = nameFragment.getName();
        cardNumber = numberFragment.getCardNumber();
        cardValidity = validityFragment.getValidity();
        cardCVV = secureCodeFragment.getValue();

        if (TextUtils.isEmpty(cardName)) {
            Toast.makeText(this, "Enter Valid Name", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(cardNumber)   || !CreditCardUtils.isValid(cardNumber.replace(" ",""))) {
            Toast.makeText(this, "Enter Valid card number", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(cardValidity) || !CreditCardUtils.isValidDate(cardValidity)) {
            Toast.makeText(this, "Enter correct validity", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(cardCVV)      ||  cardCVV.length()<3) {
            Toast.makeText(this, "Enter valid security number", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "Your card is added", Toast.LENGTH_SHORT).show();
    }

    private void setupViewPager() {
        binding.viewpager.setOffscreenPageLimit(4);

        adapter.addFragment(numberFragment);
        adapter.addFragment(nameFragment);
        adapter.addFragment(validityFragment);
        adapter.addFragment(secureCodeFragment);

        itemN = adapter.getItemCount() - 1; // TODO refactor minus 1
        binding.viewpager.setAdapter(adapter);

        binding.viewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == itemN) {
                    binding.btnNext.setText(R.string.card_btn_submit_title);
                } else {
                    binding.btnNext.setText(R.string.card_btn_next_title);
                }

                if (position == itemN) {
                    flipCard();
                    backTrack = true;
                } else if (position == itemN - 1 && backTrack) {
                    flipCard();
                    backTrack = false;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void flipCard() {
        if (mShowingBack) {
            getSupportFragmentManager().popBackStack();
            return;
        }

        mShowingBack = true;
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.animator.card_flip_right_in,
                        R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out)
                .replace(R.id.fragment_container, cardBackFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackStackChanged() {
        mShowingBack = getSupportFragmentManager().getBackStackEntryCount() > 0;
    }


    public void nextClick() {
        binding.btnNext.performClick();
    }


}
