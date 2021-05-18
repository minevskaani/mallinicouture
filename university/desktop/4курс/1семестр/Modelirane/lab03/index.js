const linear = require('./linear');
const generateMatrix = size => {
  return Array.from({length: size}, () => {
    return Array.from({length: size}, (_, j) => ~~((Number.EPSILON + Math.random()) * 10000) / 10000);
  });
};

const calculate = matrix => {
  return getLimitProbabilities(matrix).map(num => Math.round((Number.EPSILON + num) * 10000) / 10000);
};

const getLimitProbabilities = matrix => {
  const {length} = matrix;
  const coeffs = getKolmogorovCoeffs(matrix);

  return linear.solve(coeffs, Array.from({length}, (_, i) => {
    if (i === length - 1) return 1;
    return 0;
  }));
};

const getKolmogorovCoeffs = matrix => {
  const {length} = matrix;

  return Array.from({length}, (_, i) => {
    if (i !== length - 1) {
      return Array.from({length}, (_, j) => {
        if (j === i) return - matrix[i].reduce((a, b) => a + b) + matrix[i][i];

        return matrix[j][i];
      });
    }

    return Array.from({length}, () => 1);
  });
};

const formatMatrix = matrix => {
  const res = {};
  res[' '] = Array.from({length: matrix.length}, (_, i) => `e${i + 1}`)
  matrix.forEach((e, i) => {
    res[`e${i + 1}`] = e;
  });

  return res;
}

const inputSizes = [5, 10, 15];
inputSizes.forEach(inputSize => {
  const matrix = generateMatrix(inputSize);
  const result = calculate(matrix);

  console.log({size: inputSize});
  console.table(formatMatrix(matrix));

  console.table({
    'Состояние': Array.from({length: result.length}, (_, i) => `e${i + 1}`),
    'Предельные вероятности': result
  });
});