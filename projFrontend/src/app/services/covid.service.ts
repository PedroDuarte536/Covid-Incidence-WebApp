import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CovidService {

  baseApiUrl = "http://localhost:8080/covid"

  constructor(private http: HttpClient) { }

  getCases () {
    return this.http.get(this.baseApiUrl+"/cases");
  }

  getCasesByCountry (country: string) {
    return this.http.get(this.baseApiUrl+"/cases/" + country);
  }

  getCasesByDate (date: string) {
    return this.http.get(this.baseApiUrl+"/cases", { params: { date } });
  }

  getCasesByDateRange (start_date: string, end_date: string) {
    return this.http.get(this.baseApiUrl+"/cases", { params: { start_date, end_date } });
  }

  getCasesByDateAndCountry (country: string, date: string) {
    return this.http.get(this.baseApiUrl+"/cases/" + country, { params: { date } });
  }

  getCasesByDateRangeAndCountry (country:string, start_date: string, end_date: string) {
    return this.http.get(this.baseApiUrl+"/cases/" + country, { params: { start_date, end_date } });
  }


}
