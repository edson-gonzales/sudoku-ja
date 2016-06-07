import com.utils.readers.PropertiesReader;
import com.utils.writers.PropertiesWriter;
import com.utils.writers.PropertiesWriter.CONFIG;
import org.junit.Assert;
import org.junit.Test;

/**
 * This class represent the unit tests for the Properties writer and Reader classes
 *
 * @author Jose Cabrera
 */
public class PropertiesTest {
    String algorithm, outputPath, outputName, level;

    @Test
    public void verifyIfPropertiesFileIsCreatedDefaultValues() {
        PropertiesWriter propertiesWriter = new PropertiesWriter();
        propertiesWriter.setProperties();
        PropertiesReader propertiesReader = new PropertiesReader();
        algorithm = propertiesReader.getProperty(CONFIG.ALGORITHM.name());
        Assert.assertEquals("The algorithm obtained is the same that was written", CONFIG.ALGORITHM.toString(), algorithm);
        outputPath = propertiesReader.getProperty(CONFIG.OUTPUT_PATH.name());
        Assert.assertEquals("The outputPath obtained is the same that was written", CONFIG.OUTPUT_PATH.toString(), outputPath);
        outputName = propertiesReader.getProperty(CONFIG.OUTPUT_FILE_NAME.name());
        Assert.assertEquals("The outputName obtained is the same that was written", CONFIG.OUTPUT_FILE_NAME.toString(), outputName);
        level = propertiesReader.getProperty(CONFIG.LEVEL.name());
        Assert.assertEquals("The level obtained is the same that was written", CONFIG.LEVEL.toString(), level);
        propertiesWriter.closeWriter();
    }

    @Test
    public void verifyIfAlgorithmPropertyCanBeChanged() {
        PropertiesWriter propertiesWriter = new PropertiesWriter();
        propertiesWriter.setProperties();
        propertiesWriter.setProperty(CONFIG.ALGORITHM.name(), "Peter Norvig");
        PropertiesReader propertiesReader = new PropertiesReader();
        algorithm = propertiesReader.getProperty(CONFIG.ALGORITHM.name());
        Assert.assertEquals("The algorithm obtained is the same that was modified", CONFIG.ALGORITHM.toString(), algorithm);
        propertiesWriter.closeWriter();
    }

    @Test
    public void verifyIfOutPutPathPropertyCanBeChanged() {
        PropertiesWriter propertiesWriter = new PropertiesWriter();
        propertiesWriter.setProperties();
        propertiesWriter.setProperty(CONFIG.OUTPUT_PATH.name(), "C:/");
        PropertiesReader propertiesReader = new PropertiesReader();
        outputPath = propertiesReader.getProperty(CONFIG.OUTPUT_PATH.name());
        Assert.assertEquals("The outputPath obtained is the same that was written", CONFIG.OUTPUT_PATH.toString(), outputPath);
        propertiesWriter.closeWriter();
    }

    @Test
    public void verifyIfOutputNamePropertyValueCanBeChanged() {
        PropertiesWriter propertiesWriter = new PropertiesWriter();
        propertiesWriter.setProperties();
        propertiesWriter.setProperty(CONFIG.OUTPUT_FILE_NAME.name(), "Modified Name");
        PropertiesReader propertiesReader = new PropertiesReader();
        outputName = propertiesReader.getProperty(CONFIG.OUTPUT_FILE_NAME.name());
        Assert.assertEquals("The outputName obtained is the same that was written", CONFIG.OUTPUT_FILE_NAME.toString(), outputName);
        propertiesWriter.closeWriter();
    }

    @Test
    public void verifyIfLevelPropertyValueCanBeChanged() {
        PropertiesWriter propertiesWriter = new PropertiesWriter();
        propertiesWriter.setProperties();
        propertiesWriter.setProperty(CONFIG.LEVEL.name(), "Modified Name");
        PropertiesReader propertiesReader = new PropertiesReader();
        level = propertiesReader.getProperty(CONFIG.LEVEL.name());
        Assert.assertEquals("The outputName obtained is the same that was written", CONFIG.LEVEL.toString(), level);
        propertiesWriter.closeWriter();
    }

    @Test
    public void verifyIfPropertyValueCanBeChanged() {
        PropertiesWriter propertiesWriter = new PropertiesWriter();
        propertiesWriter.setProperties();
        propertiesWriter.setProperty(CONFIG.ALGORITHM.name(), "Peter Norvig");
        PropertiesReader propertiesReader = new PropertiesReader();
        algorithm = propertiesReader.getProperty(CONFIG.ALGORITHM.name());
        Assert.assertEquals("The algorithm obtained is the same that was modified", CONFIG.ALGORITHM.toString(), algorithm);
        outputPath = propertiesReader.getProperty(CONFIG.OUTPUT_PATH.name());
        Assert.assertEquals("The outputPath obtained is the same that was written", CONFIG.OUTPUT_PATH.toString(), outputPath);
        outputName = propertiesReader.getProperty(CONFIG.OUTPUT_FILE_NAME.name());
        Assert.assertEquals("The outputName obtained is the same that was written", CONFIG.OUTPUT_FILE_NAME.toString(), outputName);
        level = propertiesReader.getProperty(CONFIG.LEVEL.name());
        Assert.assertEquals("The level obtained is the same that was written", CONFIG.LEVEL.toString(), level);
        propertiesWriter.closeWriter();
    }

}