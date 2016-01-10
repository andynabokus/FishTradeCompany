package ua.com.fishtrade.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter  
public class EncryptedStringConverter implements AttributeConverter<String, String> {  
  
  private static final String STRING_ENCRYPTOR_NAME = "jpaStringEncryptor";  
  
  
  public EncryptedStringConverter() {  
  }  
  
  public String convertToDatabaseColumn(String value) {  
    if ( value == null ) {  
      return null;  
    }  
    return JpaPBEEncryptorRegistry.getInstance().getPBEStringEncryptor(STRING_ENCRYPTOR_NAME).encrypt(value);  
  }  
  
  public String convertToEntityAttribute(String value) {  
    if ( value == null ) {  
      return null;  
    }  
    return JpaPBEEncryptorRegistry.getInstance().getPBEStringEncryptor(STRING_ENCRYPTOR_NAME).decrypt(value);  
  }  
  
}  
  