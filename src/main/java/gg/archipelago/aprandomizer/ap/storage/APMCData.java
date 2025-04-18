package gg.archipelago.aprandomizer.ap.storage;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.UUID;

public class APMCData {

    @SerializedName("world_seed")
    public long world_seed;
    @SerializedName("structures")
    public HashMap<String, String> structures;
    @SerializedName("seed_name")
    public String seed_name;
    @SerializedName("player_name")
    public String player_name;
    @SerializedName("player_id")
    public int player_id;
    @SerializedName("client_version")
    public int client_version;
    @SerializedName("race")
    public boolean race = false;
    @SerializedName("quest_goal")
    public String quest_goal;

    @SerializedName("server")
    public String server;

    @SerializedName("port")
    public int port;

    @SerializedName("ftb_team_id")  // New field for FTB Team ID
    public UUID ftbTeamId;  // FTB Team ID field

    public State state = State.VALID;

    public enum State {
        VALID, MISSING, INVALID_VERSION, INVALID_SEED
    }

}
