import {Routes} from '@angular/router';
import {UserList} from './pages/user-list/user-list';

export const userRoutes : Routes = [
  { path: '', redirectTo: 'all', pathMatch: 'full' },
  { path: 'all', component: UserList}
]
