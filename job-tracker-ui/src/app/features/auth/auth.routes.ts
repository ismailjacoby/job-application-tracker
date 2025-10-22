import {Routes} from '@angular/router';
import {Login} from './pages/login/login';
import {Signup} from './pages/signup/signup';

export const authRoutes: Routes = [
  {path: '', redirectTo: 'login', pathMatch: 'full'},
  {path: 'login', component: Login},
  {path: 'signup', component: Signup}
]
