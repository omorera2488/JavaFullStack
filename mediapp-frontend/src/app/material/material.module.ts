import { MatPaginatorImpl } from './mat-paginator';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatSidenavModule, MatToolbarModule, MatIconModule, MatButtonModule,MatMenuModule, MatDividerModule, MatTableModule, MatFormFieldModule, MatInputModule, MAT_DATE_LOCALE, MatSortModule, MatPaginatorModule, MatCardModule, MatSnackBar, MatSnackBarModule, MatDialogModule, MatSelectModule, MatDatepickerModule, MatNativeDateModule, MatExpansionModule, MatAutocompleteModule, MatStepperModule, MatSlideToggleModule, MatGridListModule, MatPaginatorIntl} from '@angular/material';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    MatSidenavModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatMenuModule,
    MatDividerModule,
    MatTableModule,
    MatFormFieldModule,
    MatInputModule,
    MatSortModule,
    MatPaginatorModule,
    MatCardModule,
    MatSnackBarModule,
    MatDialogModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatExpansionModule,
    MatAutocompleteModule,
    MatStepperModule,
    MatSlideToggleModule,
    MatGridListModule
  ],
  exports:[
    MatSidenavModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatMenuModule,
    MatDividerModule,
    MatTableModule,
    MatFormFieldModule,
    MatInputModule,
    MatSortModule,
    MatPaginatorModule,
    MatCardModule,
    MatSnackBarModule,
    MatDialogModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatExpansionModule,
    MatAutocompleteModule,
    MatStepperModule,
    MatSlideToggleModule,
    MatGridListModule
  ],
  providers: [
    { provide: MatPaginatorIntl, useClass: MatPaginatorImpl},
    { provide: MAT_DATE_LOCALE, useValue: 'es-ES' },
  ]
})
export class MaterialModule { }
