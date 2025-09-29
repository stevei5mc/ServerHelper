package cn.stevei5mc.serverhelper.waterdogpe.utils;

import dev.waterdog.waterdogpe.utils.config.Configuration;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;

public class LanguageApi {
    private final Configuration language;

    public LanguageApi(Configuration language) {
        this.language = language;
    }

    public String translateString(String key) {
        return this.translateString(key, new Object[]{});
    }

    public String translateString(String key, Object... params) {
        String value = this.language.getString(key, "§cUnknown text! key: " + key);
        if (params != null && params.length > 0) {
            for (int i = 1; i < params.length + 1; i++) {
                value = value.replace("%" + i + "%", Objects.toString(params[i-1]));
            }
        }
        return value;
    }

    public void update(Configuration newLanguage) {
        boolean save = false;
        int currentVersion = language.getInt("version", 0);
        int latestVersion = newLanguage.getInt("version", 0);
        boolean shouldUpdate = (currentVersion > latestVersion || currentVersion < latestVersion) && latestVersion > 0;
        boolean noVersion = currentVersion == 0 && latestVersion == 0;
        if (shouldUpdate || noVersion) {
            HashMap<String, String> cache = new HashMap<>();
            LinkedList<String> languageKeys = new LinkedList<>(language.getKeys());

            if (!noVersion) {
                language.setInt("version", latestVersion);
            }

            for (String key : languageKeys) {
                if (newLanguage.getKeys().contains(key)) {
                    cache.put(key, language.getString(key, "§cNo language text! key: " + key));
                } else {
                    save = true;
                }
                language.remove(key);
            }

            for (String key : newLanguage.getKeys()) {
                String value;
                if (languageKeys.contains(key)) {
                    value = cache.get(key);
                } else {
                    value = newLanguage.getString(key, "§cNo language text! key: " + key);
                    save = true;
                }
                language.set(key, newLanguage.getString(key, value));
            }

            if (save) {
                language.save();
            }
        }
    }
}