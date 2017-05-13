import { Injectable } from '@angular/core';
import { Team } from './team.model';
import { AngularFireDatabase, FirebaseListObservable, FirebaseObjectObservable } from 'angularfire2/database';


@Injectable()
export class TeamService {
  games: string[] = ["DOTA 2", "League of Legends", "StarCraft 2"];
  teams: FirebaseListObservable<any[]>;

  constructor(private database: AngularFireDatabase) {
    this.teams = database.list('teams');
  }

  getTeams() {
    return this.teams;
  }

  getGames() {
    return this.games;
  }

  addTeam(team: Team) {
    this.teams.push(team);
  }

}
