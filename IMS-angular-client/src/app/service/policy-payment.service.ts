import { Injectable } from '@angular/core';
import {PaymentPolicy} from '../component/payment/PaymentPolicy';
import {Observable} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';

const headers = {headers: new HttpHeaders({'Content-Type': 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class PolicyPaymentService {
  articleURL = 'http://localhost:8080/cogent-insurance/';

  constructor(private httpClient: HttpClient) {
  }

  public getPolicy(id: number): Observable<PaymentPolicy> {
    return this.httpClient.get<PaymentPolicy>(this.articleURL + `payment/${id}`, headers);
  }
}
