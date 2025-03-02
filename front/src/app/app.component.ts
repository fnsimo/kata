import {
  Component,
  inject,
  ViewChild,
} from "@angular/core";
import { RouterModule } from "@angular/router";
import { SplitterModule } from 'primeng/splitter';
import { ToolbarModule } from 'primeng/toolbar';
import { PanelMenuComponent } from "./shared/ui/panel-menu/panel-menu.component";
import { ProductsService } from "./products/data-access/products.service";
import { Product } from "./products/data-access/product.model";

import { OverlayPanel } from 'primeng/overlaypanel';
import { OverlayPanelModule } from 'primeng/overlaypanel';
import { ToastModule } from 'primeng/toast';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.scss"],
  standalone: true,
  imports: [RouterModule, SplitterModule, ToolbarModule, PanelMenuComponent,OverlayPanelModule, ToastModule, 
    TableModule, ButtonModule],
})
export class AppComponent {
  title = "ALTEN SHOP";


  private readonly productsService = inject(ProductsService);

  public readonly productsCart = this.productsService.productsCart;

  @ViewChild('op') overlayPanel: OverlayPanel | undefined; 

  get cartItemCount(): number {
    return this.productsCart().length; 
  }



  

  removeFromCart(product: Product) {

    this.productsService.removeFromCart(product);
   // this.productsService.update(product).subscribe();

    if(this.productsCart().length<1){
  
      this.overlayPanel!.hide();

    }

  }

  calculateTotalPrice(): number {
    return this.productsCart().reduce((total, product) => total + (product.price ), 0);
  }

 
  
    
}
