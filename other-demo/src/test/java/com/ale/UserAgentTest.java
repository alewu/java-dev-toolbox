package com.ale;

import com.blueconic.browscap.BrowsCapField;
import com.blueconic.browscap.Capabilities;
import com.blueconic.browscap.ParseException;
import com.blueconic.browscap.UserAgentParser;
import com.blueconic.browscap.UserAgentService;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class UserAgentTest {

    @Test
    public void test() throws IOException, ParseException {

// create a parser with the default fields
        final UserAgentParser parser = new UserAgentService().loadParser(); // handle IOException and ParseException

        // parser can be re-used for multiple lookup calls
        final String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36";
        final Capabilities capabilities = parser.parse(userAgent);

// the default fields have getters
        final String browser = capabilities.getBrowser();
        final String browserType = capabilities.getBrowserType();
        final String browserMajorVersion = capabilities.getBrowserMajorVersion();
        final String deviceType = capabilities.getDeviceType();
        final String platform = capabilities.getPlatform();
        final String platformVersion = capabilities.getPlatformVersion();

// the custom defined fields are available
        final String renderingEngineMaker = capabilities.getValue(BrowsCapField.RENDERING_ENGINE_MAKER);

// do something with the values
    }
}
