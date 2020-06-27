package com.calzado.util;

import java.io.File;
import java.io.IOException;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;


public class ApplicationProperties {

    private static ApplicationProperties instance = null;
    private static PropertiesConfiguration configuration = null;

    public ApplicationProperties() throws IOException, ConfigurationException {
       
        // Load a properties file (which may contain variables)
Parameters params = new Parameters();
FileBasedConfigurationBuilder<PropertiesConfiguration> builder =
    new FileBasedConfigurationBuilder<PropertiesConfiguration>(PropertiesConfiguration.class)
    .configure(params.fileBased()
        .setFile(new File("configApplication.properties")));
PropertiesConfiguration config = builder.getConfiguration();

// Perform interpolation on all variables
configuration =  (PropertiesConfiguration) config.interpolatedConfiguration();
    
        
    }

    public static ApplicationProperties getInstance() throws ConfigurationException {
        if(instance == null) {
            try {
                instance = new ApplicationProperties();
            } catch (final IOException ioe) {
                ioe.printStackTrace();
            }
        }
        return instance;
    }

    public static String getValue(final String key) {
        return (String) configuration.getProperty(key);
    }

}