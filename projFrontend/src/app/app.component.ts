import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { CovidService } from 'src/app/services/covid.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  country: string = ""
  date: string = ""
  startDate: string = ""
  endDate: string = ""
  
  constructor(private covidService: CovidService) { }

  updateAll() {
    this.country = (<HTMLInputElement> document.querySelector('[name="country"]'))!.value;
    this.date = (<HTMLInputElement> document.querySelector('[name="date"]'))!.value;
    this.startDate = (<HTMLInputElement> document.querySelector('[name="start_date"]'))!.value;
    this.endDate = (<HTMLInputElement> document.querySelector('[name="end_date"]'))!.value;
  }

  submit() {

    this.updateAll()

    let request: Observable<any>;

    if (this.country == "") {
      if (this.date != "") {
        request = this.covidService.getCasesByDate(this.date);
      } else if (this.startDate != "" && this.endDate != "") {
        request = this.covidService.getCasesByDateRange(this.startDate, this.endDate);
      } else {
        request = this.covidService.getCases();
      }
    } else {
      if (this.date != "") {
        request = this.covidService.getCasesByDateAndCountry(this.country, this.date);
      } else if (this.startDate != "" && this.endDate != "") {
        request = this.covidService.getCasesByDateRangeAndCountry(this.country, this.startDate, this.endDate);
      } else {
        request = this.covidService.getCasesByCountry(this.country);
      }
    }

    request.subscribe((data: any) => {
      document.getElementById("resultCountry")!.innerHTML = data.country == null ? '' : data.country
      document.getElementById("resultValue")!.innerHTML = data.value == null ? '' : data.value
    })
  }

}
