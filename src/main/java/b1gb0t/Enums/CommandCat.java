package b1gb0t.Enums;

public enum CommandCat {
    CREATOR("owner", ":crown:", "Owner"),
    INFORMATIVE("information",":information_source:", "Information"),
    FUN("fun", ":game_die:", "Fun"),
    CORE("core", ":gear:", "Core"),
    GENERAL("general", ":military_medal:", "General"),
    UNKNOWN("nopackage", ":question:", "Misc");
    private final String packageName;
    private final String emoticon;
    private final String displayName;

    CommandCat(String packageName, String emoticon, String displayName) {

        this.packageName = packageName;
        this.emoticon = emoticon;
        this.displayName = displayName;
    }


    public static CommandCat fromPackage(String packageName) {
        if (packageName != null) {
            for (CommandCat cc : values()) {
                if (packageName.equalsIgnoreCase(cc.packageName)) {
                    return cc;
                }
            }
        }
        return UNKNOWN;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getEmoticon() {
        return emoticon;
    }

}