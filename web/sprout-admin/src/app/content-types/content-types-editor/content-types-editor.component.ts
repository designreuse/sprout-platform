import { ContentTemplate } from '../../content-template/content-template.service';
import { ContentTypesService, ContentType } from '../content-types.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-content-types-editor',
  templateUrl: './content-types-editor.component.html',
  styleUrls: ['./content-types-editor.component.css']
})
export class ContentTypesEditorComponent implements OnInit {

  item: ContentType;
  rForm: FormGroup;
  post: any; // A property for our submitted form

  // model fields
  description: string;
  name: string;
  template: ContentTemplate;
  icon: string;
  // end model fields

  addPost(post) {
    this.description = post.description;
    this.name = post.name;
  }

  loadItem(id: string) {
    if (id) {
      this.item = this.contentTypesService.items[0];
    } else {
      this.item = new ContentType();
    }
  }

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private contentTypesService: ContentTypesService) {

    this.route.params.subscribe( params => this.loadItem(params['id']) );

    this.rForm = fb.group({
      'name' : [null, Validators.compose([Validators.required, Validators.minLength(1), Validators.maxLength(255)])],
      'description' : [null, Validators.compose([Validators.required, Validators.minLength(1), Validators.maxLength(255)])],
    });
  }

  ngOnInit() {
  }

}
