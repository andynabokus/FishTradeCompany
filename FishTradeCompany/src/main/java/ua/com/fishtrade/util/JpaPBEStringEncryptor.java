package ua.com.fishtrade.util;

import java.security.Provider;

import org.jasypt.encryption.pbe.PBEStringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.PBEConfig;
import org.jasypt.exceptions.EncryptionInitializationException;
import org.jasypt.salt.SaltGenerator;

/**  
 * Jasypt String encryptor that can be used within a Jpa application.  
 * Derived from org.jasypt.hibernate4.encryptor.HibernatePBEStringEncryptor  
 *  
 */  
public final class JpaPBEStringEncryptor {  
  
    private String registeredName = null;  
    private PBEStringEncryptor encryptor = null;  
    private boolean encryptorSet = false;  
  
  
    /**  
     * Creates a new instance of <tt>JpaPBEStringEncryptor</tt>. It also  
     * creates a <tt>StandardPBEStringEncryptor</tt> for internal use, which  
     * can be overriden by calling <tt>setEncryptor(...)</tt>.  
     */  
    public JpaPBEStringEncryptor() {  
      super();  
      this.encryptor = new StandardPBEStringEncryptor();  
      this.encryptorSet = false;  
    }  
  
  
    /*  
     * For internal use only, by the Registry, when a PBEStringEncryptor  
     * is registered programmatically.  
     */  
    JpaPBEStringEncryptor(final String registeredName,  
                  final PBEStringEncryptor encryptor) {  
      this.encryptor = encryptor;  
      this.registeredName = registeredName;  
      this.encryptorSet = true;  
    }  
  
  
    /**  
     * Returns the encryptor which this object wraps.  
     *  
     * @return the encryptor.  
     */  
    public synchronized PBEStringEncryptor getEncryptor() {  
      return this.encryptor;  
    }  
  
  
    /**  
     * Sets the <tt>PBEStringEncryptor</tt> to be held (wrapped) by this  
     * object. This method is optional and can be only called once.  
     *  
     * @param encryptor the encryptor.  
     */  
    public synchronized void setEncryptor(final PBEStringEncryptor encryptor) {  
      if (this.encryptorSet) {  
        throw new EncryptionInitializationException(  
            "An encryptor has been already set: no " +  
                "further configuration possible on jpa wrapper");  
      }  
      this.encryptor = encryptor;  
      this.encryptorSet = true;  
    }  
  
  
    /**  
     * Sets the password to be used by the internal encryptor, if a specific  
     * encryptor has not been set with <tt>setEncryptor(...)</tt>.  
     *  
     * @param password the password to be set for the internal encryptor  
     */  
    public void setPassword(final String password) {  
      if (this.encryptorSet) {  
        throw new EncryptionInitializationException(  
            "An encryptor has been already set: no " +  
                "further configuration possible on jpa wrapper");  
      }  
      final StandardPBEStringEncryptor standardPBEStringEncryptor =  
          (StandardPBEStringEncryptor) this.encryptor;  
      standardPBEStringEncryptor.setPassword(password);  
    }  
  
  
    /**  
     * Sets the password to be used by the internal encryptor (as a char[]), if a specific  
     * encryptor has not been set with <tt>setEncryptor(...)</tt>.  
     *  
     * @since 1.8  
     * @param password the password to be set for the internal encryptor  
     */  
    public void setPasswordCharArray(final char[] password) {  
      if (this.encryptorSet) {  
        throw new EncryptionInitializationException(  
            "An encryptor has been already set: no " +  
                "further configuration possible on jpa wrapper");  
      }  
      final StandardPBEStringEncryptor standardPBEStringEncryptor =  
          (StandardPBEStringEncryptor) this.encryptor;  
      standardPBEStringEncryptor.setPasswordCharArray(password);  
    }  
  
  
    /**  
     * Sets the algorithm to be used by the internal encryptor, if a specific  
     * encryptor has not been set with <tt>setEncryptor(...)</tt>.  
     *  
     * @param algorithm the algorithm to be set for the internal encryptor  
     */  
    public void setAlgorithm(final String algorithm) {  
      if (this.encryptorSet) {  
        throw new EncryptionInitializationException(  
            "An encryptor has been already set: no " +  
                "further configuration possible on jpa wrapper");  
      }  
      final StandardPBEStringEncryptor standardPBEStringEncryptor =  
          (StandardPBEStringEncryptor) this.encryptor;  
      standardPBEStringEncryptor.setAlgorithm(algorithm);  
    }  
  
  
    /**  
     * Sets the key obtention iterations to be used by the internal encryptor,  
     * if a specific encryptor has not been set with <tt>setEncryptor(...)</tt>.  
     *  
     * @param keyObtentionIterations to be set for the internal encryptor  
     */  
    public void setKeyObtentionIterations(final int keyObtentionIterations) {  
      if (this.encryptorSet) {  
        throw new EncryptionInitializationException(  
            "An encryptor has been already set: no " +  
                "further configuration possible on jpa wrapper");  
      }  
      final StandardPBEStringEncryptor standardPBEStringEncryptor =  
          (StandardPBEStringEncryptor) this.encryptor;  
      standardPBEStringEncryptor.setKeyObtentionIterations(  
          keyObtentionIterations);  
    }  
  
  
    /**  
     * Sets the salt generator to be used by the internal encryptor,  
     * if a specific encryptor has not been set with <tt>setEncryptor(...)</tt>.  
     *  
     * @param saltGenerator the salt generator to be set for the internal  
     *           encryptor.  
     */  
    public void setSaltGenerator(final SaltGenerator saltGenerator) {  
      if (this.encryptorSet) {  
        throw new EncryptionInitializationException(  
            "An encryptor has been already set: no " +  
                "further configuration possible on jpa wrapper");  
      }  
      final StandardPBEStringEncryptor standardPBEStringEncryptor =  
          (StandardPBEStringEncryptor) this.encryptor;  
      standardPBEStringEncryptor.setSaltGenerator(saltGenerator);  
    }  
  
  
    /**  
     * Sets the name of the JCE provider to be used by the internal encryptor,  
     * if a specific encryptor has not been set with <tt>setEncryptor(...)</tt>.  
     *  
     * @since 1.3  
     *  
     * @param providerName the name of the JCE provider (already registered)  
     */  
    public void setProviderName(final String providerName) {  
      if (this.encryptorSet) {  
        throw new EncryptionInitializationException(  
            "An encryptor has been already set: no " +  
                "further configuration possible on jpa wrapper");  
      }  
      final StandardPBEStringEncryptor standardPBEStringEncryptor =  
          (StandardPBEStringEncryptor) this.encryptor;  
      standardPBEStringEncryptor.setProviderName(providerName);  
    }  
  
  
    /**  
     * Sets the JCE provider to be used by the internal encryptor,  
     * if a specific encryptor has not been set with <tt>setEncryptor(...)</tt>.  
     *  
     * @since 1.3  
     *  
     * @param provider the JCE provider to be used  
     */  
    public void setProvider(final Provider provider) {  
      if (this.encryptorSet) {  
        throw new EncryptionInitializationException(  
            "An encryptor has been already set: no " +  
                "further configuration possible on jpa wrapper");  
      }  
      final StandardPBEStringEncryptor standardPBEStringEncryptor =  
          (StandardPBEStringEncryptor) this.encryptor;  
      standardPBEStringEncryptor.setProvider(provider);  
    }  
  
  
    /**  
     * Sets the type of String output ("base64" (default), "hexadecimal") to  
     * be used by the internal encryptor,  
     * if a specific encryptor has not been set with <tt>setEncryptor(...)</tt>.  
     *  
     * @since 1.3  
     *  
     * @param stringOutputType the type of String output  
     */  
    public void setStringOutputType(final String stringOutputType) {  
      if (this.encryptorSet) {  
        throw new EncryptionInitializationException(  
            "An encryptor has been already set: no " +  
                "further configuration possible on jpa wrapper");  
      }  
      final StandardPBEStringEncryptor standardPBEStringEncryptor =  
          (StandardPBEStringEncryptor) this.encryptor;  
      standardPBEStringEncryptor.setStringOutputType(stringOutputType);  
    }  
  
  
    /**  
     * Sets the PBEConfig to be used by the internal encryptor,  
     * if a specific encryptor has not been set with <tt>setEncryptor(...)</tt>.  
     *  
     * @param config the PBEConfig to be set for the internal encryptor  
     */  
    public void setConfig(final PBEConfig config) {  
      if (this.encryptorSet) {  
        throw new EncryptionInitializationException(  
            "An encryptor has been already set: no " +  
                "further configuration possible on jpa wrapper");  
      }  
      final StandardPBEStringEncryptor standardPBEStringEncryptor =  
          (StandardPBEStringEncryptor) this.encryptor;  
      standardPBEStringEncryptor.setConfig(config);  
    }  
  
  
    /**  
     * Encrypts a message, delegating to wrapped encryptor.  
     *  
     * @param message the message to be encrypted.  
     * @return the encryption result.  
     */  
    public String encrypt(final String message) {  
      if (this.encryptor == null) {  
        throw new EncryptionInitializationException(  
            "Encryptor has not been set into jpa wrapper");  
      }  
      return this.encryptor.encrypt(message);  
    }  
  
  
    /**  
     * Decypts a message, delegating to wrapped encryptor  
     *  
     * @param encryptedMessage the message to be decrypted.  
     * @return the result of decryption.  
     */  
    public String decrypt(final String encryptedMessage) {  
      if (this.encryptor == null) {  
        throw new EncryptionInitializationException(  
            "Encryptor has not been set into jpa wrapper");  
      }  
      return this.encryptor.decrypt(encryptedMessage);  
    }  
  
  
  
    /**  
     * Sets the registered name of the encryptor and adds it to the registry.  
     *  
     * @param registeredName the name with which the encryptor will be  
     *            registered.  
     */  
    public void setRegisteredName(final String registeredName) {  
      if (this.registeredName != null) {  
        // It had another name before, we have to clean  
        JpaPBEEncryptorRegistry.getInstance().  
            unregisterJpaPBEStringEncryptor(this.registeredName);  
      }  
      this.registeredName = registeredName;  
      JpaPBEEncryptorRegistry.getInstance().  
          registerJpaPBEStringEncryptor(this);  
    }  
  
    /**  
     * Returns the name with which the wrapped encryptor is registered at  
     * the registry.  
     *  
     * @return the registered name.  
     */  
    public String getRegisteredName() {  
      return this.registeredName;  
    }  
  
} 
