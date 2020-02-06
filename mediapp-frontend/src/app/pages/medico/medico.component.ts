import { MedicoService } from './../../_service/medico.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource, MatSort, MatPaginator, MatSnackBar, MatDialog, MatSnackBarModule } from '@angular/material';
import { Medico } from 'src/app/_model/medico';
import { MedicoEdicionComponent } from './medico-edicion/medico-edicion.component';
import { switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-medico',
  templateUrl: './medico.component.html',
  styleUrls: ['./medico.component.css']
})
export class MedicoComponent implements OnInit {

  displayedColumns = ['idMedico', 'nombres', 'apellidos', 'acciones'];
  dataSource: MatTableDataSource<Medico>;
  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  constructor(
    private medicoService: MedicoService,
    private dialog: MatDialog,
    private snackbar: MatSnackBar
  ) { }

  ngOnInit() {
    this.medicoService.mensajeCambio.subscribe(data => {
      this.snackbar.open(data, 'Mensaje', { duration: 2000 });
    });

    this.medicoService.medicoCambio.subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
    });

    this.medicoService.listar().subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
    });
  }

  filtrar(valor: string) {
    this.dataSource.filter = valor.trim().toLowerCase();
  }

  eliminar(medico: Medico) {
    this.medicoService.eliminar(medico.idMedico).pipe(switchMap(() => {
      return this.medicoService.listar();
    })).subscribe(data => {
      this.medicoService.medicoCambio.next(data);
      this.medicoService.mensajeCambio.next('Medico eliminado');
    });
  }

  abrirDialog(medico?: Medico) {
    let obj = medico != null ? medico : new Medico();
    this.dialog.open(MedicoEdicionComponent, {
      width: '250px',
      data: obj
    });

  }

}
