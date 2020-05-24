package tr.com.havelsan.demo.utils.types;

public enum  LogType {
Save("save"),
    Update("update"),
    Delete("Del"),
    Select("Select");

    private String logType;

    LogType(String type) {
        this.logType = type;
    }

    public String getLogType() {
        return logType;
    }
}
