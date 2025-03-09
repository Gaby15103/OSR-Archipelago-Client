package gg.archipelago.aprandomizer.managers.teammanager;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.ftb.mods.ftbteams.api.FTBTeamsAPI;
import dev.ftb.mods.ftbteams.api.Team;
import dev.ftb.mods.ftbteams.api.TeamManager;
import dev.ftb.mods.ftbteams.api.property.PrivacyProperty;
import dev.ftb.mods.ftbteams.api.property.TeamProperties;
import dev.ftb.mods.ftbteams.api.property.TeamPropertyCollection;
import net.minecraft.server.level.ServerPlayer;

public class TeamHelper {
    private TeamManager teamManager;
    private Team archipelagoTeam;
    private final String TEAM_NAME = "Archipelago-Team";
    public boolean createTeam(ServerPlayer player) throws CommandSyntaxException {

        archipelagoTeam = teamManager.createPartyTeam(player, TEAM_NAME, "Description of the team", null);
        TeamPropertyCollection properties = archipelagoTeam.getProperties();
        properties.set(TeamProperties.FREE_TO_JOIN, true);
        archipelagoTeam.markDirty();
        return archipelagoTeam != null;
    }
    public boolean isPlayerInArchipelagoTeam(ServerPlayer player) throws CommandSyntaxException {
        if (teamManager == null){
            teamManager = FTBTeamsAPI.api().getManager();
        }
        if (teamManager.getTeamByName(TEAM_NAME).isPresent()){
            if (archipelagoTeam == null){
                archipelagoTeam = teamManager.getTeamByName(TEAM_NAME).get();
            }
            return archipelagoTeam.getMembers().contains(player.getUUID());
        }else{
            return createTeam(player);
        }
    }
    public String getTeamName(){return TEAM_NAME;}
    public Team getTeam(){return archipelagoTeam;}
}
