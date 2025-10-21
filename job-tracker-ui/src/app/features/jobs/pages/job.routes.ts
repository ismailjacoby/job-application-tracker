import {Routes} from '@angular/router';
import {JobList} from './job-list/job-list';
import {JobForm} from './job-form/job-form';

export const jobRoutes : Routes = [
  { path: '', redirectTo: 'all', pathMatch: 'full' },
  {path:'all', component: JobList},
  {path:'create', component: JobForm}
];
