import {Component, Input, OnInit} from '@angular/core';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {PaymentService} from '../../../service/payment.service';
import {Router} from '@angular/router';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {PaymentDto} from '../PaymentDto';
import {ModalComponent} from '../modal/modal.component';
import {Element as StripeElement, Elements, ElementsOptions, StripeService} from 'ngx-stripe';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {
  @Input() price;
  @Input() description;
  @Input() name;

  error: any;

  elements: Elements;
  card: StripeElement;

  elementsOptions: ElementsOptions = {
    locale: 'en'
  };

  constructor(
    public modalService: NgbModal,
    private stripeService: StripeService,
    private paymentService: PaymentService,
    private router: Router
  ) {
  }

  public stripeForm = new FormGroup({
    name: new FormControl('', Validators.required)
  });

  ngOnInit() {
    this.stripeService.elements(this.elementsOptions)
      .subscribe(elements => {
        this.elements = elements;
        // Only mount the element the first time
        if (!this.card) {
          this.card = this.elements.create('card', {
            style: {
              base: {
                iconColor: '#666EE8',
                color: '#31325F',
                lineHeight: '40px',
                fontWeight: 300,
                fontFamily: '"Helvetica Neue", Helvetica, sans-serif',
                fontSize: '18px',
                '::placeholder': {
                  color: '#CFD7E0'
                }
              }
            }
          });
          this.card.mount('#card-element');
        }
      });
  }

  buy() {
    const name = this.stripeForm.get('name').value;
    this.stripeService
      .createToken(this.card, {name})
      .subscribe(result => {
        if (result.token) {
          const paymentIntentDto: PaymentDto = {
            token: result.token.id,
            amount: this.price,
            currency: 'usd',
            description: this.description
          };
          this.paymentService.makePayment(paymentIntentDto).subscribe(
            data => {
              this.abrirModal(data[`id`], this.name, data[`description`], data[`amount`]);
              this.router.navigate(['/']);
            }
          );
          this.error = undefined;
        } else if (result.error) {
          this.error = result.error.message;
        }
      });
  }

  abrirModal(id: string, name: string, description: string, price: number) {
    const modalRef = this.modalService.open(ModalComponent);
    modalRef.componentInstance.id = id;
    modalRef.componentInstance.name = name;
    modalRef.componentInstance.description = description;
    modalRef.componentInstance.price = price;
  }
}
