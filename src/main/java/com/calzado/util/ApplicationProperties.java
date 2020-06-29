package com.calzado.util;

import java.io.File;
import java.io.IOException;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;


public class ApplicationProperties {

    private static ApplicationProperties instance;
    private static PropertiesConfiguration configuration;

    public ApplicationProperties() throws IOException, ConfigurationException {       
     Parameters param = new Parameters();   
     FileBasedConfigurationBuilder<PropertiesConfiguration> builder =
        new FileBasedConfigurationBuilder<PropertiesConfiguration>(PropertiesConfiguration.class)
        .configure(param.fileBased().setFile(new File("configApplication.properties")));    
     configuration = (PropertiesConfiguration) builder.getConfiguration().interpolatedConfiguration();    
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