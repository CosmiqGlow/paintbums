package de.cosmiqglow.paintbums.teams;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TeamService {

  private static TeamService instance = new TeamService(new ArrayList<>());
  private List<PaintbumsTeam> list;
  private int counter;

  private TeamService(List<PaintbumsTeam> list) {
    this.list = list;
    counter = 0;
  }

  public static TeamService getInstance() {
    return instance;
  }

  public boolean add(PaintbumsTeam team) {
    if (list.contains(team)) return false;

    list.add(team);
    return true;
  }

  public boolean remove(PaintbumsTeam team) {
    if (!list.contains(team)) return false;

    list.remove(team);
    return true;
  }

  public void distribute(List<Player> playerList) {
    do {
      int i = ThreadLocalRandom.current().nextInt(0, playerList.size() + 1);
      distribute(playerList.get(i));
      playerList.remove(i);
    } while (!playerList.isEmpty());
  }

  //TODO: perhaps insert check if user is in team (via return boolean)
  public void distribute(Player player) {
    if (list.isEmpty()) return;
    list.get(counter++%list.size()).join(player);
  }

  public List<PaintbumsTeam> getTeams() {
    return list;
  }
}
