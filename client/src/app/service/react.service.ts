import {Injectable} from '@angular/core';
import {ApiService} from './api.service';
import {ConfigService} from './config.service';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { map } from 'rxjs/operators';
import { Router } from '@angular/router';





@Injectable()
export class ReactService {

  constructor(
    private apiService: ApiService,
    private config: ConfigService,
    private http: HttpClient,
    private router: Router

  ) {
  }

  add(reaction){
    console.log(this.config.react_url+"/react")
    return this.apiService.post(this.config.react_url + "/react", JSON.stringify(reaction)).subscribe(()=>{
      this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
        this.router.navigate(['/posts']);})
    })
    
  }
}
