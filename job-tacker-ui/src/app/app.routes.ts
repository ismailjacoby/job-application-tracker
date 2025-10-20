import { Routes } from '@angular/router';
import { DashboardLayout } from './features/layout/dashboard-layout/dashboard-layout';

export const routes: Routes = [
  {
    path: 'app',
    loadComponent: () =>
      import('./features/layout/dashboard-layout/dashboard-layout').then(
        (m) => m.DashboardLayout
      ),
    children: [
      {
        path: 'dashboard',
        loadComponent: () =>
          import('./features/dashboard/pages/dashboard/dashboard').then(
            (m) => m.Dashboard
          ),
      },
      {
        path: 'jobs/create',
        loadComponent: () =>
          import('./features/jobs/pages/job-form/job-form').then(
            (m) => m.JobForm
          ),
      },
    ],
  },
];
