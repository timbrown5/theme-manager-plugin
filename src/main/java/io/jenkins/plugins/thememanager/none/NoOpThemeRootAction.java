package io.jenkins.plugins.thememanager.none;

import hudson.Extension;
import hudson.model.UnprotectedRootAction;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import org.apache.commons.io.IOUtils;
import org.kohsuke.accmod.Restricted;
import org.kohsuke.accmod.restrictions.NoExternalUse;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;
import org.kohsuke.stapler.WebMethod;

import static io.jenkins.plugins.thememanager.none.NoOpThemeManagerFactory.USER_LOGO_CSS;
import static io.jenkins.plugins.thememanager.none.NoOpThemeManagerFactory.THEME_URL_NAME;
import static java.util.Objects.requireNonNull;

// Note: I would like to not require a ThemeRootAction - as this means all
// other themes need to inherit/duplicate this behavior. I tried adding a
// RootAction to the package above, but it never seemed to be loaded.

@Extension
@Restricted(NoExternalUse.class)
public class NoOpThemeRootAction implements UnprotectedRootAction {
  // Hard code CSS here (rather than loading a separate file) so that
  // classes which inherit this can use it (as the relative path to the)
  // CSS would change.
  private final String logoCss = "#header .logo {\n" +
                                  "\tbackground: url(<LOGO_DATA>);\n" +
                                  "\tbackground-repeat: no-repeat;\n" +
                                  "\tbackground-size: auto 40px, cover;\n" +
                                  "\tbackground-position: 7px 7px;\n" +
                                  "}\n" +
                                  "\n" +
                                  "#jenkins-home-link {\n" +
                                  "\tdisplay: None !important;\n" +
                                  "}\n";

  @Override
  public String getIconFileName() {
    return null;
  }

  @Override
  public String getDisplayName() {
    return null;
  }

  @Override
  public String getUrlName() {
    return THEME_URL_NAME;
  }

  @WebMethod(name = "user-logo.css")
  public void doDarkThemeCss(StaplerRequest req, StaplerResponse res) throws IOException {
    res.setContentType("text/css");
    // Somehow get either the Logo URL, or the Data URI from ThemeManagerPageDecorator
    // and use it here in the replacement.
    String cssUri = "https://www.jenkins.io/images/logos/actor/256.png";
    String updatedCss = logoCss.replace("<LOGO_DATA>", cssUri);
    res.getWriter().print(updatedCss);
  }
}
