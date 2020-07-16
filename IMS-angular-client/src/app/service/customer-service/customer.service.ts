import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private baseUrl = 'http://localhost:8080/api/customers';

  constructor(private http: HttpClient) {
  }

  getCustomer(customerId: string): Observable<any> {
    return this.http.get(this.baseUrl + '/' + customerId);
  }

  createCustomer(customer: Object): Observable<Object> {
    return this.http.post(this.baseUrl, customer);
  }

  updateCustomer(customer: Object, id: string): Observable<Object> {
    return this.http.put(this.baseUrl + '/' + id, customer);
  }

  deleteCustomer(id: string): Observable<any> {
    return this.http.delete(this.baseUrl + '/' + id);
  }

  getAllCustomers(): Observable<any> {
    return this.http.get(this.baseUrl);
  }
}
