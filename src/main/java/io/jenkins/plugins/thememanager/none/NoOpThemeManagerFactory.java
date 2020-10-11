package io.jenkins.plugins.thememanager.none;

import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.Extension;
import io.jenkins.plugins.thememanager.Theme;
import io.jenkins.plugins.thememanager.ThemeManagerFactory;
import io.jenkins.plugins.thememanager.ThemeManagerFactoryDescriptor;
import org.jenkinsci.Symbol;
import org.kohsuke.accmod.Restricted;
import org.kohsuke.accmod.restrictions.NoExternalUse;
import org.kohsuke.stapler.DataBoundConstructor;

/** Disables any theming, other than the optional custom logo. */
@Restricted(NoExternalUse.class)
public class NoOpThemeManagerFactory extends ThemeManagerFactory {
  public final static String USER_LOGO_CSS = "user-logo.css";
  public static final String THEME_URL_NAME = "theme-none";

  @DataBoundConstructor
  public NoOpThemeManagerFactory() {}

  @Override
  public Theme getTheme() {
    return Theme.builder()
            .withCssUrl(getCssUrl())
            .build();
  }

  @Extension
  @Symbol("none")
  public static class NoOpThemeManagerFactoryDescriptor extends ThemeManagerFactoryDescriptor {

    @NonNull
    @Override
    public String getDisplayName() {
      return "Default";
    }

    @Override
    public String getThemeCssSuffix() {
        return USER_LOGO_CSS;
    }

    @Override
    public ThemeManagerFactory getInstance() {
        return new NoOpThemeManagerFactory();
    }

    @Override
    public String getThemeId() {
      return "none";
    }
  }
}
