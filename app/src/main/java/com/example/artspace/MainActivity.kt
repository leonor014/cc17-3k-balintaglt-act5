package com.example.artspace

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var currentImageIndex = 0

    // List of artworks (added descriptions)
    private val artworks = listOf(
        Artwork(R.drawable.palay_maiden,"Palay Maiden","Fernando Amorsolo","Palay Maiden from 1920 which depicts a Filipina woman symbolizing the Philippines' staple crop of rice."),
        Artwork(R.drawable.cordillera,"Untitled(Cordillera)","Ben A. Laxina",""),
        Artwork(R.drawable.kalinga_madonna_and_child,"Kalinga Madonna and Child","Jojo Sabalvaro Tan","Since the beginning of Christianity, Mary and Jesus were depicted based on the ethnicity of the painter or place of worship. So you would find images of Mary and Jesus as  Italian, French, African, Greek, Armenian, Chinese, Spanish or Filipino. In honor of the birth of our new grandniece, I decided to paint the Madonna and Child inspired by the Kalinga culture."),
        Artwork(R.drawable.sunset, "Sunset in the Rockies", "Albert Bierstadt", "Sunset in the Rockies is a famous oil painting, originally by American artist Albert Bierstadt in 1866, with the style of romanticism."),
        Artwork(R.drawable.the_blood_compact, "The Blood Compact", "Juan Luna", "Blood Compact, refers to the pledge made in 1565 between the native chieftain Sikatuna and the Spanish conqueror Legaspi who landed on Bohol Island in the same year."
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find views
        val imageView: ImageView = findViewById(R.id.image_view)
        val artTitleTextView: TextView = findViewById(R.id.art_title_text)
        val artistNameTextView: TextView = findViewById(R.id.artis_name)
        val artDescriptionTextView: TextView = findViewById(R.id.tag_state_description) // New TextView for artwork description
        val previousButton: Button = findViewById(R.id.previousButton)
        val nextButton: Button = findViewById(R.id.nextButton)

        // Display initial artwork
        displayArtwork(imageView, artTitleTextView, artistNameTextView, artDescriptionTextView)

        // Set up button click listeners
        previousButton.setOnClickListener {
            currentImageIndex = if (currentImageIndex > 0) {
                currentImageIndex - 1
            } else {
                artworks.size - 1
            }
            displayArtwork(imageView, artTitleTextView, artistNameTextView, artDescriptionTextView)
        }

        nextButton.setOnClickListener {
            currentImageIndex = (currentImageIndex + 1) % artworks.size
            displayArtwork(imageView, artTitleTextView, artistNameTextView, artDescriptionTextView)
        }
    }

    // Function to display the current artwork
    private fun displayArtwork(
        imageView: ImageView,
        artTitle: TextView,
        artistName: TextView,
        artDescription: TextView // Add the new TextView for description
    ) {
        val artwork = artworks[currentImageIndex]
        imageView.setImageResource(artwork.imageResId)
        artTitle.text = artwork.title
        artistName.text = artwork.artist
        artDescription.text = artwork.description
    }

    // Handle configuration changes such as orientation
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        // Re-apply the current artwork to the new layout
        val imageView: ImageView = findViewById(R.id.image_view)
        val artTitleTextView: TextView = findViewById(R.id.art_title_text)
        val artistNameTextView: TextView = findViewById(R.id.artis_name)
        val artDescriptionTextView: TextView = findViewById(R.id.tag_state_description) // Re-add description TextView

        // Display the current artwork
        displayArtwork(imageView, artTitleTextView, artistNameTextView, artDescriptionTextView)
    }

    // Define your Artwork data class
    data class Artwork(
        val imageResId: Int,
        val title: String,
        val artist: String,
        val description: String // Add description field to the Artwork data class
    )
}