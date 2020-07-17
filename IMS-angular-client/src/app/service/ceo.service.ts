import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CeoService {

  private baseUrl = 'http://localhost:8080/api/ceo';

  constructor(private http: HttpClient) {
  }

  getCeo(ceoId: string): Observable<any> {
    return this.http.get(this.baseUrl + '/' + ceoId);
  }

  createCeo(ceo: Object): Observable<Object> {
    return this.http.post(this.baseUrl, ceo);
  }

  updateCeo(ceo: Object, id: string): Observable<Object> {
    return this.http.put(this.baseUrl + '/' + id, ceo);
  }

  deleteCeo(id: string): Observable<any> {
    return this.http.delete(this.baseUrl + '/' + id);
  }

  getAllCeos(): Observable<any> {
    return this.http.get(this.baseUrl);
  }
}
