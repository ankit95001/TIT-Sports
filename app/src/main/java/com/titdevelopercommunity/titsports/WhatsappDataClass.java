package com.titdevelopercommunity.titsports;

public class WhatsappDataClass {
    String gameName;
    String gameCoordinatorName;
    String gameCoordinatorNumber;
    String gameLink;

    public WhatsappDataClass() {
    }

    public WhatsappDataClass(String gameName, String gameCoordinatorName, String gameCoordinatorNumber, String gameLink) {
        this.gameName = gameName;
        this.gameCoordinatorName = gameCoordinatorName;
        this.gameCoordinatorNumber = gameCoordinatorNumber;
        this.gameLink = gameLink;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameCoordinatorName() {
        return gameCoordinatorName;
    }

    public void setGameCoordinatorName(String gameCoordinatorName) {
        this.gameCoordinatorName = gameCoordinatorName;
    }

    public String getGameCoordinatorNumber() {
        return gameCoordinatorNumber;
    }

    public void setGameCoordinatorNumber(String gameCoordinatorNumber) {
        this.gameCoordinatorNumber = gameCoordinatorNumber;
    }

    public String getGameLink() {
        return gameLink;
    }

    public void setGameLink(String gameLink) {
        this.gameLink = gameLink;
    }


}
