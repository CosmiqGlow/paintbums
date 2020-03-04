package de.cosmiqglow.paintbums.scoreboards;

import de.cosmiqglow.paintbums.teams.PaintbumsTeam;
import de.cosmiqglow.paintbums.teams.TeamService;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ScoreboardService {

  private static ScoreboardService instance = new ScoreboardService();

  private final Scoreboard board;
  private Objective objective;

  private ScoreboardService() {
    board = Objects.requireNonNull(Bukkit.getScoreboardManager()).getNewScoreboard();
    objective = board.registerNewObjective("game", "dummy", " §e§lPaintbums ");

    Team basic = board.registerNewTeam("basic");

    basic.setAllowFriendlyFire(false);
    basic.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.ALWAYS);
    basic.setColor(ChatColor.WHITE);
    basic.setPrefix(ChatColor.WHITE+"");
    basic.setDisplayName("Sternchen");
  }

  public static ScoreboardService getInstance() {
    return instance;
  }

  public void setupGame() {
    objective.setDisplaySlot(DisplaySlot.SIDEBAR);

    this.objective.getScore("§r").setScore(1);
    this.objective.getScore("§l§r").setScore(-1);

    TeamService.getInstance().getTeams().forEach(team -> objective.getScore(team.getName()).setScore(0));
  }

  public void registerTeams(List<PaintbumsTeam> teams) {
    teams.stream().map(team -> team.getName()).forEach(board::registerNewTeam);
  }

  public void setupPlayer(Player player) {
    board.getTeam("basic").addEntry(player.getName());
    player.setScoreboard(board);
  }

  public void registerPlayer(Player player, PaintbumsTeam team) {
    board.getTeam(team.getName()).addEntry(player.getName());
  }

  public void unregisterPlayer(Player player, PaintbumsTeam team) {
    board.getTeam(team.getName()).removeEntry(player.getName());
  }

  public void updatePoints(PaintbumsTeam team) {
    objective.getScore(team.getName()).setScore(team.getPoints());
  }

  /*
  public void clearBoard() {

  }*/
}
