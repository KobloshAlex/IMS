import { Injectable } from '@angular/core';
import {Article} from '../component/payment/Article';
import {Observable} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';

const headers = {headers: new HttpHeaders({'Content-Type': 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class ArticleService {
  articleURL = 'http://localhost:8080/cogent-insurance/';

  constructor(private httpClient: HttpClient) {
  }

  public getPolicy(id: number): Observable<Article> {
    return this.httpClient.get<Article>(this.articleURL + `payment/${id}`, headers);
  }
}
