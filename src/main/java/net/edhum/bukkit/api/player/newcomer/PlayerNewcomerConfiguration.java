package net.edhum.bukkit.api.player.newcomer;

public class PlayerNewcomerConfiguration {

    private String group;
    private String language;
    private long money;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }
}
