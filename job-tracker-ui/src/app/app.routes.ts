import { Routes } from '@angular/router';
import { authGuard } from './core/guards/auth-guard';
import {Home} from './features/public/pages/home/home';
import {guestGuard} from './core/guards/guest-guard';
import {NotFound} from './features/public/pages/not-found/not-found';

export const routes: Routes = [
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: 'home', component: Home},
  {
    path: 'auth',
    canActivate: [guestGuard],
    loadChildren: () =>
      import('./features/auth/auth.routes').then((m) => m.authRoutes),
  },
  {
    path: 'app',
    canActivate: [authGuard],
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
        path: 'jobs',
        loadChildren: () =>
          import('./features/jobs/job.routes').then(
            (m) => m.jobRoutes
          ),
      },
      {
        path: 'users',
        loadChildren: () =>
          import('./features/users/user.routes').then(
            (m) => m.userRoutes
          ),
      },
    ],
  },

  {
    path: '**',
    component: NotFound
  },
];
