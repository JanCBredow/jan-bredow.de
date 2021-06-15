package bredow.jan.webserverdemo.inject.provider;

import bredow.jan.webserverdemo.conf.Configuration;
import bredow.jan.webserverdemo.conf.db.DatabaseConfiguration;
import bredow.jan.webserverdemo.conf.db.DatabaseCredentials;
import bredow.jan.webserverdemo.conf.db.DatabasePoolConfiguration;
import bredow.jan.webserverdemo.log.ConsoleLogger;
import bredow.jan.webserverdemo.util.ExitCode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Singleton;
import org.springframework.stereotype.Component;

import java.io.*;

@Singleton
@Component
public final class ConfigurationProvider {
  private static final ConsoleLogger LOGGER = ConsoleLogger.of(ConfigurationProvider.class);

  private static final String CONFIGURATION_FILE_NAME = "config.json";
  private static final File CONFIG_FILE = new File(CONFIGURATION_FILE_NAME);

  private static final Gson GSON =
      new GsonBuilder()
          .serializeNulls()
          .setPrettyPrinting()
          .enableComplexMapKeySerialization()
          .create();

  public Configuration get() {
    return readOrCreate();
  }

  private Configuration readOrCreate() {
    if (CONFIG_FILE.exists()) {
      return readFromFile();
    }
    return createNewFile();
  }

  private Configuration createNewFile() {
    Configuration configuration =
        Configuration.createWith(
            DatabaseConfiguration.create(
                "localhost",
                3006,
                "janbredowde",
                DatabaseCredentials.withCredentials("username", "12345 is a bad password")),
            DatabasePoolConfiguration.empty());

    save(configuration);

    return configuration;
  }

  private void save(Configuration configuration) {
    try (var writer = new BufferedWriter(new FileWriter(CONFIG_FILE))) {
      writer.write(GSON.toJson(configuration));
      writer.flush();
      LOGGER.info("Configurationfile was created");
    } catch (IOException failure) {
      var exitCode = ExitCode.CONFIGURATION_READ_ERROR;
      exitWith(
          LOGGER
              .error("An error occurred while trying to save the Configuration-File")
              .space(5)
              .warn("We Sorry! :/")
              .warn("We tried to write an update to the Config-File, called: ")
              .warn("    " + CONFIG_FILE.getName())
              .warn(
                  "but we had trouble to write to the File or access it. See errorcodes.txt for further details")
              .space()
              .errorIf("Full Error Message: " + failure.getMessage(), debugEnabled()),
          exitCode);
    }
  }

  @SuppressWarnings("unused")
  private void exitWith(ConsoleLogger unused, ExitCode exitCode) {
    System.err.println(exitCode.errorMessage());
    System.exit(exitCode.exitCodeNumerical());
  }

  private Configuration readFromFile() {
    try (var reader = new BufferedReader(new FileReader(CONFIG_FILE))) {
      return GSON.fromJson(reader, Configuration.class);
    } catch (IOException failure) {
      var exitCode = ExitCode.CONFIGURATION_READ_ERROR;
      exitWith(
          LOGGER
              .error("An error occurred while trying to load the Configuration-File")
              .space(5)
              .warn("We Sorry! :/")
              .warn("We tried to lookup the Configuration-File, called")
              .warn("    " + CONFIG_FILE.getName())
              .warn("but we could not find or access it. See errorcodes.txt for further details")
              .space()
              .errorIf("Full Error Message: " + failure.getMessage(), debugEnabled()),
          exitCode);
      return null;
    }
  }

  private boolean debugEnabled() {
    return System.getenv("JCB_DEBUG_ENABLED") != null;
  }
}
