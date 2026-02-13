package com.gw.myearnings.app_screens



import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import com.gw.myearnings.R
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gw.myearnings.utils.dateFormat
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.FileInvoiceDollar


//@Composable
//fun AppOverviewScreen(navController: NavController) {
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//
//            // Scrollable content
//            .verticalScroll(rememberScrollState())
//
//
//            .background(colorResource(id = R.color.lavender))
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(horizontal = 12.dp, vertical = 16.dp)
//                .background(colorResource(id = R.color.lavender))
//                // 👇 THIS is the key line
//                // It pushes CONTENT below the status bar
//                .statusBarsPadding()
//        ) {
//            DateHeader()
//            Spacer(modifier = Modifier.height(8.dp))
//
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//
//
//
//        }
//    }
//
//}
//
//
//@Preview(showBackground = true)
//@Composable
//fun AppOverviewScreenPreview() {
//    AppOverviewScreen(navController = rememberNavController())
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppOverviewScreen(navController: NavController) {
    val context = LocalContext.current
    val appVersion = "1.0.0" // Ideally pull this from BuildConfig


    var showIpDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("About") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // --- Header Section ---
            Spacer(modifier = Modifier.height(24.dp))

            // App Icon Placeholder
            Surface(
                modifier = Modifier.size(100.dp),
                shape = RoundedCornerShape(24.dp),
                color = MaterialTheme.colorScheme.primaryContainer
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = FontAwesomeIcons.Solid.FileInvoiceDollar,
                        contentDescription = null,
                        modifier = Modifier.size(48.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "My Earnings", //  App Name
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Version $appVersion",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(32.dp))

            // --- Info Section ---
            AboutSection(title = "General") {
                AboutItem(
                    icon = Icons.Default.Info,
                    label = "Description",
                    subLabel = "MyEarnings is a simple income tracking app designed to help " +
                            "you record money earned from different sources and monitor " +
                            "how you spend and save it. Whether your income comes from " +
                            "side hustles, small business activities, freelance work," +
                            " or part-time shifts, MyEarnings" +
                            " helps you stay organized and aware of your financial progress."
                            ,
                )
//                AboutItem(
//                    icon = Icons.Default.Info,
//                    label = "Check for Updates",
//                    onClick = { /* Check play store link */ }
//                )
            }

            AboutSection(title = "Connect & Support") {
//                AboutItem(
//                    icon = Icons.Default.Email,
//                    label = "Website",
//                    onClick = { /* Open Website Intent */ }
//                )
                AboutItem(
                    icon = Icons.Default.Email,
                    label = "Contact Support",
                    onClick = { /* Open Email Intent */
                        val intent = Intent(Intent.ACTION_SEND).apply {
                            type = "message/rfc822"
                            putExtra(Intent.EXTRA_EMAIL, arrayOf("email@gmail.com"))
                            putExtra(Intent.EXTRA_SUBJECT, "App Support & Feedback")
                            putExtra(Intent.EXTRA_TEXT, "Hello, am ...")
                        }
                        try {
                            context.startActivity(Intent.createChooser(intent, "Contact Support"))
                        } catch (e: Exception) {
                            Toast.makeText(context, "No email app found.", Toast.LENGTH_SHORT).show()
                        }
                    }
                )
            }

            AboutSection(title = "Legal") {
                AboutItem(
                    icon = Icons.Default.Lock,
                    label = "Privacy Policy",
                    onClick = { /* Open URL */
                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            "https://user.github.io/MyEarningsApp/privacyPolicy.html".toUri()
                        )
                        context.startActivity(intent)
                    }
                )
                AboutItem(
                    icon = Icons.Default.Person,
                    label = "Intellectual Property",
                    onClick = {
                        showIpDialog = true
                    }
                )
            }

            Text(
                text = "Made with ❤️ By Mango Place Apps",
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(24.dp),
                color = Color.Gray
            )

            Spacer(modifier = Modifier.navigationBarsPadding())
        }
    }



    if (showIpDialog) {
        IntellectualPropertyDialog(onDismiss = { showIpDialog = false },
        )
    }

}

@Composable
fun AboutSection(title: String, content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
        )
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
            )
        ) {
            Column {
                content()
            }
        }
    }
}

@Composable
fun AboutItem(
    icon: ImageVector,
    label: String,
    subLabel: String? = null,
    onClick: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = onClick != null) { onClick?.invoke() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = label, style = MaterialTheme.typography.bodyLarge)
            if (subLabel != null) {
                Text(
                    text = subLabel,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        }
    }
}




@Composable
fun IntellectualPropertyDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = "Intellectual Property Notice", fontWeight = FontWeight.Bold)
        },
        text = {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Text(
                    text = """
              Intellectual Property

The software, design, and original content of this application were created by Gw Labs. While Gw Labs retains ownership of its original work, we support fair use and respect the open-source ecosystem that makes projects like this possible.

Third-Party Assets

This application uses third-party assets under their respective licenses.

Icons are provided by Font Awesome Icons.

Gw Labs does not claim ownership of any third-party assets used within this application. All trademarks, logos, and brand names belong to their respective owners.

We gratefully acknowledge and respect all intellectual property rights.
                    """.trimIndent(),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Close")
            }
        },
        shape = RoundedCornerShape(24.dp) // Matches your modern UI theme
    )
}




