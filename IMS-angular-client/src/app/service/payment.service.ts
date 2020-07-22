import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {PaymentDto} from '../component/payment/PaymentDto';

const contentType = {headers: new HttpHeaders({'Content-Type': 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  stripeURL = 'http://localhost:8080/cogent-insurance/stripe/';

  constructor(private httpClient: HttpClient) {}

  public makePayment(paymentIntentDto: PaymentDto): Observable<string> {
    return this.httpClient.post<string>(this.stripeURL + 'paymentintent', paymentIntentDto, contentType);
  }

  public confirmPayment(id: string): Observable<string> {
    return this.httpClient.post<string>(this.stripeURL + `confirm/${id}`, {}, contentType);
  }

  public cancelPayment(id: string): Observable<string> {
    return this.httpClient.post<string>(this.stripeURL + `cancel/${id}`, {}, contentType);
  }

}
