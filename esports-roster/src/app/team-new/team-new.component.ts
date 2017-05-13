import { Component, OnInit } from '@angular/core';
import { Team } from '../team.model';
import { FormArray, FormBuilder, FormGroup, FormControl, Validators } from "@angular/forms";
import { TeamService } from '../team.service';

@Component({
  selector: 'app-team-new',
  templateUrl: './team-new.component.html',
  styleUrls: ['./team-new.component.css']
})
export class TeamNewComponent implements OnInit {
  teamForm: FormGroup;
  games: string[];

  constructor(private fb: FormBuilder, private teamService: TeamService) { }

  ngOnInit() {
    this.teamForm = this.fb.group({
      name: ['', Validators.required],
      game: ['', Validators.required],
      players: this.fb.array([]),
      description: ['', Validators.required],
      image_src: ['', Validators.required]
    })
    this.games = this.teamService.getGames();
  }

  get players(): FormArray {
    return this.teamForm.get('players') as FormArray;
  }

  addPlayer() {
    this.players.push(this.fb.group({
      name: [''],
      age: [''],
      description: [''],
      image_src: ['']}));
  }

  removePlayer(index: number) {
    this.players.removeAt(index);
  }

  addTeam() {
    var {name, game, players, description, image_src} = this.teamForm.value;
    var newTeam = new Team(name, game, players, description, image_src);
    this.teamService.addTeam(newTeam);
    this.teamForm.reset();
  }

}
