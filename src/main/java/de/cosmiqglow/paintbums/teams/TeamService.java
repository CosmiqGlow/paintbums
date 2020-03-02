package de.cosmiqglow.paintbums.teams;

import org.bukkit.entity.Player;
import java.util.List;

public class TeamService {

  List<Team> list;

  public TeamService(List<Team> list) {
    this.list = list;
  }

  public void distribute(List<Player> playerList) {
    for (Player player: playerList) {
      distribute(player);
    }
  }

  public void distribute(Player player) {

  }

  public List<Team> getTeams() {
    return list;
  }





}
