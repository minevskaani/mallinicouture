#!/bin/bash

keytool -genkeypair -alias senderKeyPair -keyalg RSA -keysize 2048 \
  -dname "CN=AM" -validity 365 -storetype PKCS12 \
  -keystore sender_keystore.p12 -storepass crg02asd94jfh31fhks82hdfb1ubfbcdu2jaq2

keytool -exportcert -alias senderKeyPair -storetype PKCS12 \
  -keystore sender_keystore.p12 -file \
  sender_certificate.cer -rfc -storepass crg02asd94jfh31fhks82hdfb1ubfbcdu2jaq2

keytool -importcert -alias receiverKeyPair -storetype PKCS12 \
  -keystore receiver_keystore.p12 -file \
  sender_certificate.cer -rfc -storepass crg02asd94jfh31fhks82hdfb1ubfbcdu2jaq2