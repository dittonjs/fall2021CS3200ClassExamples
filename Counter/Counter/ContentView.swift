//
//  ContentView.swift
//  Counter
//
//  Created by Joseph Ditton on 12/6/21.
//

import SwiftUI

struct ContentView: View {
    @State private var count = 0
    
    var body: some View {
        VStack {
            HStack {
                Text("\(count)").font(.largeTitle)
            }
            HStack {
                Button("Decrement") {
                    count -= 1
                }
                Button("Increment") {
                    count += 1
                }
            }
            
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
