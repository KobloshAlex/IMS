import { Component, OnInit } from '@angular/core';
import {PaymentPolicy} from '../PaymentPolicy';
import {PolicyPaymentService} from '../../../service/policy-payment.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-article-details',
  templateUrl: './article-details.component.html',
  styleUrls: ['./article-details.component.css']
})
export class ArticleDetailsComponent implements OnInit {

  article: PaymentPolicy;

  constructor(
    private articleService: PolicyPaymentService,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.loadArticle();
  }

  loadArticle(): void {
    const id = this.activatedRoute.snapshot.params.id;
    this.articleService.getPolicy(id).subscribe(
      data => {
        this.article = data;
      },
      err => {
        console.log(err);
      }
    );
  }
}
