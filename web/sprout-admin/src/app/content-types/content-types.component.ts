import { ContentTypesService, ContentType } from './content-types.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-content-types',
  templateUrl: './content-types.component.html',
  styleUrls: ['./content-types.component.css']
})
export class ContentTypesComponent implements OnInit {

  items: ContentType[];

  addItem(): void {
    this.router.navigate(['content-types-editor']);
  }

  editItem(item: ContentType): void {
    this.router.navigate(['content-types-editor', {id: item.id}]);
  }

  constructor(private router: Router, contentTypes: ContentTypesService) {
    contentTypes.findAll().subscribe(data => {
      this.items = data._embedded.contentTypes;
    }, err => {
      console.error('Failed to get contentTypes');
    });
  }

  ngOnInit() {
  }

}
