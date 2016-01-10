package ua.com.fishtrade.util;

import java.util.HashMap;

import org.jasypt.encryption.pbe.PBEStringEncryptor;

/**  
 * Maintains a registry of Jasypt JPA encryptors. Currently only String encryptor is supported.  
 * Derived from org.jasypt.hibernate4.encryptor.HibernatePBEEncryptorRegistry  
 *  
 */  
public final class JpaPBEEncryptorRegistry {  
  
  
  // The singleton instance  
  private static final JpaPBEEncryptorRegistry instance =  
      new JpaPBEEncryptorRegistry();  
  
  
  // Registry maps  
  private final HashMap<String, JpaPBEStringEncryptor> stringEncryptors = new HashMap<String, JpaPBEStringEncryptor>();  
  
  
  /**  
   * Returns the singleton instance of the registry.  
   *  
   * @return the registry.  
   */  
  public static JpaPBEEncryptorRegistry getInstance() {  
    return instance;  
  }  
  
  // The registry cannot be externally instantiated.  
  private JpaPBEEncryptorRegistry() {  
    super();  
  }  
  
  
  /**  
   * Registers a <tt>PBEStringEncryptor</tt> object with the specified  
   * name.  
   *  
   * @param registeredName the registered name.  
   * @param encryptor the encryptor to be registered.  
   */  
  public synchronized void registerPBEStringEncryptor(  
      final String registeredName, final PBEStringEncryptor encryptor) {  
    final JpaPBEStringEncryptor jpaEncryptor =  
        new JpaPBEStringEncryptor(registeredName, encryptor);  
    this.stringEncryptors.put(registeredName, jpaEncryptor);  
  }  
  
  
  
  // Not public: this is used from  
  // JpaPBEStringEncryptor.setRegisteredName.  
  synchronized void registerJpaPBEStringEncryptor(  
      final JpaPBEStringEncryptor jpaEncryptor) {  
    this.stringEncryptors.put(  
        jpaEncryptor.getRegisteredName(),  
        jpaEncryptor);  
  }  
  
  
  // Not public: this is used from  
  // JpaPBEStringEncryptor.setRegisteredName.  
  synchronized void unregisterJpaPBEStringEncryptor(final String name) {  
    this.stringEncryptors.remove(name);  
  }  
  
  
  /**  
   * Returns the <tt>PBEStringEncryptor</tt> registered with the specified  
   * name (if exists).  
   *  
   * @param registeredName the name with which the desired encryptor was  
   *    registered.  
   * @return the encryptor, or null if no encryptor has been registered with  
   *     that name.  
   */  
  public synchronized PBEStringEncryptor getPBEStringEncryptor(  
      final String registeredName) {  
    final JpaPBEStringEncryptor jpaEncryptor =  
        (JpaPBEStringEncryptor) this.stringEncryptors.get(registeredName);  
    if (jpaEncryptor == null) {  
      return null;  
    }  
    return jpaEncryptor.getEncryptor();  
  }  
  
}  
