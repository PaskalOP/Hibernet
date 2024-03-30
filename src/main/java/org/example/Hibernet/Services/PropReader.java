package org.example.Hibernet.Services;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

public class PropReader {
    public static String urlForFlyway(){
        try(InputStream input = PropReader.class.getClassLoader()
                .getResourceAsStream("flyway.properties")) {
            Properties properties = new Properties();
            if(input==null){
                System.out.println("Sorry, unable to find application.properties");
                return null;
            }

            properties.load(input);
            return new StringBuilder("jdbc:postgresql://")
                    .append(properties.getProperty("postgres.db.host"))
                    .append(":")
                    .append(properties.getProperty("postgres.db.port"))
                    .append("/")
                    .append(properties.getProperty("postgres.db.database"))
                    .append("?currentSchema=public")
                    .toString();

        }catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public static String passwordForFlyway(){
        try(InputStream input = PropReader.class.getClassLoader()
                .getResourceAsStream("flyway.properties")) {

            Properties properties = new Properties();
            if(input==null){
                System.out.println("Sorry, unable to find application.properties");
                return null;
            }
            properties.load(input);
            return properties.getProperty("postgres.db.password");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    public static String userNameForFlyway(){
       return Objects.requireNonNull(getProperties("flyway.properties"))
               .map(properties -> properties.getProperty("postgres.db.username"))
               .orElse(null);
    }

    public static String urlForHibernate(){
        return Objects.requireNonNull(getProperties("hibernate.properties"))
                .map(properties -> properties.getProperty("hibernate.connection.url"))
                .orElse(null);
    }
    public static String passwordForHibernate(){
        return Objects.requireNonNull(getProperties("hibernate.properties"))
                .map(properties -> properties.getProperty("hibernate.connection.password"))
                .orElse(null);
    }
    public static String userNameForHibernate(){
        return Objects.requireNonNull(getProperties("hibernate.properties"))
                .map(properties -> properties.getProperty("hibernate.connection.username"))
                .orElse(null);
    }
    private static Optional<Properties> getProperties(String fileName) {
        try (InputStream input = PropReader.class.getClassLoader()
                .getResourceAsStream(fileName)) {

            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");
                return null;
            }

            prop.load(input);

            return Optional.of(prop);
        } catch (IOException ex) {
            ex.printStackTrace();
            return Optional.empty();
        }
    }
}
