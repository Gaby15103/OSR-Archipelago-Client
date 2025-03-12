package gg.archipelago.aprandomizer.managers.teammanager;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.architectury.event.EventResult;
import dev.ftb.mods.ftbquests.events.ObjectCompletedEvent;
import dev.ftb.mods.ftbquests.quest.Quest;
import dev.ftb.mods.ftbteams.FTBTeams;
import dev.ftb.mods.ftbteams.FTBTeamsAPIImpl;
import dev.ftb.mods.ftbteams.api.FTBTeamsAPI;
import dev.ftb.mods.ftbteams.api.Team;
import dev.ftb.mods.ftbteams.api.TeamManager;
import dev.ftb.mods.ftbteams.api.event.PlayerJoinedPartyTeamEvent;
import dev.ftb.mods.ftbteams.api.property.TeamProperties;
import dev.ftb.mods.ftbteams.api.property.TeamPropertyCollection;
import gg.archipelago.aprandomizer.APRandomizer;
import gg.archipelago.aprandomizer.ap.storage.APMCData;
import gg.archipelago.aprandomizer.common.Utils.Utils;
import gg.archipelago.aprandomizer.managers.questManager.QuestManager;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class TeamHelper {
    private TeamManager teamManager;
    private Optional<Team> archipelagoTeam;
    private final String TEAM_NAME = "Archipelago-Team";
    private UUID teamId;

    public TeamHelper(){
        PlayerJoinedPartyTeamEvent.PLAYER_JOINED_PARTY.register((event) -> {
            if (teamId != null){
                if (event.getTeam().getId() == teamId){
                    Utils.sendMessageToAll(event.getPlayer().getName() + " Joined the Archipelago Team");
                    // then I can unlock things that I could unlock for the player
                }
            }
        });
    }
    public boolean createTeam(ServerPlayer player) throws CommandSyntaxException {

        archipelagoTeam = Optional.ofNullable(teamManager.createPartyTeam(player, TEAM_NAME, "Description of the team", null));
        assert archipelagoTeam.isPresent();
        TeamPropertyCollection properties = archipelagoTeam.get().getProperties();
        properties.set(TeamProperties.FREE_TO_JOIN, true);
        archipelagoTeam.get().markDirty();
        APRandomizer.getApmcData().ftbTeamId = archipelagoTeam.get().getId();
        APRandomizer.saveAPMCData();
        teamId = archipelagoTeam.get().getTeamId();
        return archipelagoTeam.isPresent();
    }
    public boolean isPlayerInArchipelagoTeam(ServerPlayer player) throws CommandSyntaxException {
        if (teamManager == null){
            Utils.sendMessageToAll("Getting the team manager.");
            teamManager = FTBTeamsAPI.api().getManager();
        }
        Utils.sendMessageToAll("team Manager Loaded.");
        if (APRandomizer.getApmcData().ftbTeamId == null){
            Utils.sendMessageToAll("Creating the Team because the apmcData teamId is null.");
            createTeam(player);
            assert archipelagoTeam.isPresent();
            Utils.sendMessageToAll("The Team " + archipelagoTeam.get().getName() + " has been create");
        }
        Utils.sendMessageToAll("TeamId of apmcData is not null.");
        if (teamId == null){
            Utils.sendMessageToAll("Assigning teamId from apmcData to local teamId.");
            teamId = APRandomizer.getApmcData().ftbTeamId;
        }
        Utils.sendMessageToAll("TeamId assigned.");
        archipelagoTeam = teamManager.getTeamByID(teamId);
        if (archipelagoTeam.isPresent()){
            Utils.sendMessageToAll("Team Present.");
            assert  teamManager.getTeamForPlayer(player).isPresent();
            Utils.sendMessageToAll("Player " + player.getName() + " is part of the team "
                    + teamManager.getTeamForPlayer(player).get().getName());
            return teamManager.getTeamForPlayer(player).get() == archipelagoTeam.get();
        }else{
            Utils.sendMessageToAll("Team not Present, Creating a Team.");
            archipelagoTeam = teamManager.getTeamByID(teamId);
            assert archipelagoTeam.isPresent();
            return archipelagoTeam.get().getMembers().contains(player.getUUID());
        }
    }

    public String getTeamName(){return TEAM_NAME;}
    public Team getTeam(){
        assert archipelagoTeam.isPresent();
        return archipelagoTeam.get();
    }
}
