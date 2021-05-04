package ralseiii.skyfabric.utils;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import net.minecraft.client.MinecraftClient;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.scoreboard.ScoreboardPlayerScore;
import net.minecraft.scoreboard.Team;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SbChecks {
    public static Boolean isSkyblock = false;
    public static Boolean isCatacombs = false;
    public static void checkSkyblock() {
        List<String> list = new ArrayList<>();
        MinecraftClient client = MinecraftClient.getInstance();
        Scoreboard scoreboard = client.world.getScoreboard();
        ScoreboardObjective objective = scoreboard.getObjectiveForSlot(1);
        Collection<ScoreboardPlayerScore> scores = scoreboard.getAllPlayerScores(objective);
        List<ScoreboardPlayerScore> listScoreboard = scores.stream()
                .filter(input -> input != null && input.getPlayerName() != null && !input.getPlayerName().startsWith("#"))
                .collect(Collectors.toList());
        if (list.size() > 15) {
            scores = Lists.newArrayList(Iterables.skip(listScoreboard, scores.size() - 15));
        } else {
            scores = listScoreboard;
        }

        for (ScoreboardPlayerScore score : scores) {
            Team team = scoreboard.getPlayerTeam(score.getPlayerName());
            String text = team.getPrefix().getString() + team.getSuffix().getString();
            if (text.trim().length() > 0)
                list.add(text);
        }

        list.add(objective.getDisplayName().getString());
        Collections.reverse(list);

        String scoreboardString = list.toString();
        if (list.get(list.size() - 1).equals("www.hypixel.net")) {
            if (list.get(0).toString().contains("SKYBLOCK")) {
                isSkyblock = true;
                if (scoreboardString.contains("The Catacombs")) {
                    isCatacombs = true;
                }
            }

        }
     client.player.sendMessage(Text.of("sb.get.0: " + list.get(0).toString() + " sb.last: " + list.get(list.size() - 1)), false);
    }
}
