package com.solvd.repairserviceclasses;

enum SkillLevelEnum {
    BEGINNER("Beginner", "Basic troubleshooting skills"),
    INTERMEDIATE("Intermediate", "Advanced hardware and software knowledge"),
    EXPERT("Expert", "Specialized expertise in complex repairs");

    private final String levelName;
    private final String description;

    SkillLevelEnum(String levelName, String description) {
        this.levelName = levelName;
        this.description = description;
    }

    public String getLevelName() {
        return levelName;
    }

    public String getDescription() {
        return description;
    }
}
