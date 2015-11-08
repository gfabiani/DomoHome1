package com.fabiani.domohome.app;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.UUID;

final class Command {
    static final Integer[] sWhereChoices = new Integer[99];
    private static final String JSON_ID = "id";
    private static final String JSON_COMMAND_TITLE = "command_title";
    private static final String JSON_GROUP_TITLE = "group_title";
    private static final String JSON_WHAT = "what";
    private static final String JSON_WHO = "who";
    private static final String JSON_WHO_SPINNER_POSITION = "who_spinner_position";
    private static final String JSON_WHERE = "where";
    private static final String JSON_WHERE_SPINNER_POSITION = "where_spinner_position";
    private UUID mId;
    private String mCommandTitle;
    private String mGroupTitle;
    private int mWhat;
    private int mWho;
    private int mWhoSpinnerPosition;
    private int mWhere;
    private int mWhereSpinnerPosition;

    enum WhoChoice {SCENARIOS(0), LIGHTING(1),AUTOMATISM(2),ELECTRICAL_LOADS(3),WARMING_MANAGEMENT(4), ANTITHEFT(5),
        BASIC_VIDEO_DOORPHONE(6),GATEWAY_MANAGEMENT(13),CEN_COMMANDS(15),SOUND_DIFFUSION(16), MH200N_SCENARIOS(17),
        POWER_MANAGEMENT(18), SOUND_DIFFUSION_2(22), CEN_PLUS_ETC(25), AUTOMATION_DIAGNOSTICS(1001),
        THERMOREGULATION_DIAGNOSTICS(1004),DEVICE_DIAGNOSTICS(1013);

        private  int mValue=0;

        WhoChoice(int value){
            mValue=value;
        }

        public int getValue() {
            return mValue;
        }
    }

    Command() {
        mId = UUID.randomUUID();
        for (int i = 0; i < sWhereChoices.length; i++)
            sWhereChoices[i] = i+1;
    }

   // JSON  object extraction
    public Command(JSONObject json) throws JSONException {
        mId = UUID.fromString(json.getString(JSON_ID));// pay attention to this!
        mCommandTitle = json.getString(JSON_COMMAND_TITLE);
        mGroupTitle=json.getString(JSON_GROUP_TITLE);
        mWhat = json.getInt(JSON_WHAT);
        mWho = json.getInt(JSON_WHO);
        mWhoSpinnerPosition = json.getInt(JSON_WHO_SPINNER_POSITION);
        mWhere = json.getInt(JSON_WHERE);
        mWhereSpinnerPosition = json.getInt(JSON_WHERE_SPINNER_POSITION);
    }

    //JSON object creation
    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_ID, mId.toString());
        json.put(JSON_COMMAND_TITLE, mCommandTitle);
        json.put(JSON_GROUP_TITLE,mCommandTitle);
        json.put(JSON_WHAT, mWhat);
        json.put(JSON_WHO, mWho);
        json.put(JSON_WHO_SPINNER_POSITION, mWhoSpinnerPosition);
        json.put(JSON_WHERE, mWhere);
        json.put(JSON_WHERE_SPINNER_POSITION, mWhereSpinnerPosition);
        return json;
    }


    //getters and setters
    public UUID getId() {
        return mId;
    }

    public void setCommandTitle(String commandTitle) {
        mCommandTitle = commandTitle;
    }

    public String getCommandTitle() {
        return mCommandTitle;
    }

    public void setGroupTitle(String title) {
        mGroupTitle = title;
    }

    public String getGroupTitle() {
        return mGroupTitle;
    }

    public void setWho(int who) {
        mWho = who;
    }

    public int getWho() {
        return mWho;
    }

    public int getWhat() {
        return mWhat;
    }


    public void setWhat(int what) {
        mWhat = what;
    }

    public int getWhere() {
        return mWhere;
    }

    public void setWhere(int where) {
        mWhere = where;
    }

    public void setWhoSpinnerPosition(int whoSpinnerPosition) {
        mWhoSpinnerPosition = whoSpinnerPosition;
    }

    public int getWhoSpinnerPosition() {
        return mWhoSpinnerPosition;
    }

    public void setWhereSpinnerPosition(int whereSpinnerPosition) {
        mWhereSpinnerPosition = whereSpinnerPosition;
    }

    public int getWhereSpinnerPosition() {
        return mWhereSpinnerPosition;
    }

    @Override
    public String toString() {
        return mCommandTitle;
    }
}
