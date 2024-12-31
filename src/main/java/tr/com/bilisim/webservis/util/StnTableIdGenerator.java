package tr.com.bilisim.webservis.util;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.HibernateException;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StnTableIdGenerator implements IdentifierGenerator {

    private static StnTableIdGenerator instance; // Singleton örneği
    private Long currentId = null; // Geçerli ID değeri
    private Long sessionId = null; // Geçerli ID değeri
    private final Object lock = new Object(); // Eşzamanlama için kilit nesnesi

    // Özel yapıcı
    private StnTableIdGenerator() {}

    // Singleton örneğini döndüren metod
    public static StnTableIdGenerator getInstance() {
        if (instance == null) {
            synchronized (StnTableIdGenerator.class) {
                if (instance == null) {
                    instance = new StnTableIdGenerator();
                }
            }
        }
        return instance;
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        if (currentId == null || sessionId==currentId) {
            synchronized (lock) {
                if (currentId == null || sessionId==currentId) { // Double-checked locking
                    try {
                        // Hibernate 6'da bağlantıyı elde etmek için jdbcCoordinator kullanılıyor
                        Connection connection = session.getJdbcCoordinator().getLogicalConnection().getPhysicalConnection();

                        // Değeri SELECT ile alıp ardından UPDATE ile artırma işlemi
                        PreparedStatement psSelect = connection.prepareStatement("SELECT NEXT_HIGH_VALUE FROM ERPSAT.T_STN_HIBERNATE_UNIQUE_KEY FOR UPDATE");
                        ResultSet rs = psSelect.executeQuery();

                        if (rs.next()) {
                            Long nextHighValue = rs.getLong("NEXT_HIGH_VALUE");

                            // NEXT_HIGH_VALUE'ı 1 artır
                            PreparedStatement psUpdate = connection.prepareStatement("UPDATE ERPSAT.T_STN_HIBERNATE_UNIQUE_KEY SET NEXT_HIGH_VALUE = NEXT_HIGH_VALUE + 1");
                            psUpdate.executeUpdate();
                            psUpdate.close();

                            // 32767 ile çarp ve currentId'ye ata
                            currentId = nextHighValue * 32767;
                            sessionId = currentId+32767;
                        }
                        rs.close();
                        psSelect.close();

                    } catch (SQLException e) {
                        throw new HibernateException("Could not retrieve or increment ID", e);
                    }
                }
            }
        }

        // currentId'yi bir artırarak geri döndür
        return currentId++;
    }
}
