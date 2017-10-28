import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { APP_BASE_HREF } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [];
const routing = RouterModule.forRoot(routes);

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from '../material/material.module';
import { AppMenuComponent } from './app-menu.component';
import { MenuModule } from '@savantly/ngx-menu';
import { SecurityService } from '@savantly/ngx-security';
import { AppMenuService } from './app-menu.service';
import { HttpClientModule } from '@angular/common/http';

describe('AppMenuComponent', () => {
  let component: AppMenuComponent;
  let fixture: ComponentFixture<AppMenuComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AppMenuComponent ],
      imports: [routing, RouterModule, HttpClientModule, BrowserAnimationsModule, MaterialModule, MenuModule.forRoot()],
      providers: [SecurityService, AppMenuService, {provide: APP_BASE_HREF, useValue: '/'}]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AppMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
