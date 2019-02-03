import { Component, OnInit } from '@angular/core';
import { TaskService } from '../shared/task/task.service';
import { GiphyService } from '../shared/giphy/giphy.service';

@Component({
  selector: 'app-car-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css']
})
export class TaskListComponent implements OnInit {
  cars: Array<any>;
  searchStr = "";
  skip = 0;


  public get carList(): Array<any>[]{
    if (this.cars) {
      let list = this.cars.filter(e => {
        return e.name.toLowerCase().indexOf(this.searchStr.toLowerCase()) >= 0 ||
        e.category.toLowerCase().indexOf(this.searchStr.toLowerCase()) >= 0 
      })

      return list.slice(this.skip, this.skip + 5);
    }
    return [];
  }

  constructor(private carService: TaskService, private giphyService: GiphyService) { }

  ngOnInit() {
    this.carService.getAll().subscribe(data => {
      this.cars = data;
      for (const car of this.cars) {
        this.giphyService.get(car.category).subscribe(url => car.giphyUrl = url);
      }
    });
  }

  public toggleDetails(item: any) {
    item.open = !item.open;
  }

  public setComplete(item: any) {
    item.complete = true;
  }

  public prevItems() {
    if (this.skip >= 5) {
      this.skip = this.skip - 5;
    }
  }

  public nextItems() {
    this.skip = this.skip + 5;
  }
}
