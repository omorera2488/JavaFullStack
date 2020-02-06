import { Examen } from './../../_model/examen';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource, MatSort, MatPaginator, MatSnackBar } from '@angular/material';
import { ExamenService } from 'src/app/_service/examen.service';
import { ActivatedRoute } from '@angular/router';
import { switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-examen',
  templateUrl: './examen.component.html',
  styleUrls: ['./examen.component.css']
})
export class ExamenComponent implements OnInit {
  
  displayedColumns = ['id', 'nombre', 'descripcion', 'acciones'];
  dataSource: MatTableDataSource<Examen>;
  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  
  constructor(
    private examenService: ExamenService,
    private snackBar : MatSnackBar,
    public route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.examenService.examenCambio.subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });

    this.examenService.mensajeCambio.subscribe(data => {
      this.snackBar.open(data, 'Mensaje', {
        duration: 2000,
      });
    });

    this.examenService.listar().subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  filtrar(valor: string) {
    this.dataSource.filter = valor.trim().toLowerCase();
  }

  eliminar(examen: Examen) {
    this.examenService.eliminar(examen.idExamen).pipe(switchMap(() => {
      return this.examenService.listar();
    })).subscribe(data => {
      this.examenService.examenCambio.next(data);
      this.examenService.mensajeCambio.next('Examen eliminado');
    });
  }

}
